import React, { useState, useEffect } from 'react';
import axios from 'axios';
import {useNavigate, useParams} from 'react-router-dom';

const AllDirectories = () => {
  const [directories, setDirectories] = useState([]);
  const navigate = useNavigate();
  useEffect(() => {
      const getDirectories = async() => {
          const response = await axios.get(`/api/directories/findAll`);
          setDirectories(response.data);
    }
    getDirectories();
}, []
)
    const [department, setDepartment] = useState("");
    const [directoryName, setDirectoryName] = useState("");
    const onSubmit = async (e) => {
        e.preventDefault();
        try{
            const response = await axios.post(`/api/directories`, {department, directoryName});
            setDepartment("");
            setDirectoryName("");
            setDirectories(prev => ([ response.data, ...prev]));
        }catch(error){
            console.error("Error");
        }
    }
return (
    <div className = "all-directories-container">
        <div className = "all-directories-header">Корпоративная база знаний с чат-ботом</div>
        <form className = "create-directory" onSubmit = {onSubmit}>
            <input placeholder = "Введите отдел" value={department} onChange = {(e)=>{setDepartment(e.target.value)}}/>
            <input placeholder = "Введите имя папки" value={directoryName} onChange = {(e)=>{setDirectoryName(e.target.value)}}/>
            <button type="submit">Сохранить</button>
            </form>
        <div className = "all-directories">
        {!!(directories.length === 0) && 'Корпоративная база знаний пуста'}
        {directories.map((directory) => (
            <div className = "directory" onClick = {()=> {navigate(`/directories/${directory.directoryId}`)}}><img src = "https://cdn-icons-png.flaticon.com/512/3767/3767084.png"/>{directory.directoryName}</div>
        ))}
        </div>
    </div>
    )
};

export default AllDirectories;