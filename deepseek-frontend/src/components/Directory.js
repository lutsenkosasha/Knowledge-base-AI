import React, { useState, useEffect } from 'react';
import axios from 'axios';
import {useNavigate, useParams} from 'react-router-dom';

const Directory = () => {
    const navigate = useNavigate();
    const {id} = useParams();
    const [directory, setDirectory] = useState(null);
      useEffect(() => {
          const getDirectory = async() => {
          try{
              const response = await axios.get(`/api/directories/${id}`);
              setDirectory(response.data);
          }catch(error){
            navigate(-1);
          }
        }
        getDirectory();
    }, []
    )
    return (
    <div className = "all-directories-container">
        <div>Отдел: {directory?.department}</div>
        <div>Имя папки: {directory?.directoryName}</div>
        <button onClick = {()=> {navigate(`/chat/${id}`)}}>Задать вопрос чат-боту</button>
    </div>
    )
}

export default Directory;