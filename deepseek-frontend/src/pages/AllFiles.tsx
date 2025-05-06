import React, { useState, useEffect } from 'react';
import axios from '../api/axiosInstance';
import {useNavigate} from 'react-router-dom';
import type { DirectoryFile } from '../types/types';
import {useParams} from 'react-router-dom';

const AllFiles = () => {
    const [files, setFiles] = useState<DirectoryFile[]>([]);
    const {id} = useParams();

    const navigate = useNavigate();
    useEffect(() => {
      const getFiles = async() => {
          const response = await axios.get(`files/${id}`);
          setFiles(response.data);
    }
    getFiles();
}, []
)
    const [fileName, setFileName] = useState("");
    const onSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        try{
            const response = await axios.post(`files`, {fileName, directoryId: id});
            setFileName("");
            setFiles(prev => ([response.data, ...prev]));
        }catch(error){
            console.error("Error");
        }
    }
return (
    <div className = "all-files-container">
        <div className = "all-files-header">Содержимое папки:</div>
        <form className = "create-directory" onSubmit = {onSubmit}>
            <input placeholder = "Введите имя файла" value={fileName} onChange = {(e)=>{setFileName(e.target.value)}}/>
            <button type="submit">Сохранить</button>
            </form>
        <div className = "all-files">
        {!!(files.length === 0) && 'Корпоративная база знаний пуста'}
        {files.map((file) => (
            <div className = "file"><img src = "https://cdn-icons-png.flaticon.com/512/3720/3720094.png" alt=""/>{file.fileName}</div>
        ))}
        </div>
    </div>
    )
};

export default AllFiles;