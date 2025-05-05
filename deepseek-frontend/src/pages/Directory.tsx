import React, { useState, useEffect } from 'react';
import axios from '../api/axiosInstance';
import {useNavigate, useParams} from 'react-router-dom';
import type { Directory as DirectoryType } from '../types/types';
import AllFiles from './AllFiles';

const Directory = () => {
    const navigate = useNavigate();
    const {id} = useParams();
    const [directory, setDirectory] = useState<DirectoryType | null>(null);
      useEffect(() => {
          const getDirectory = async() => {
          try{
              const response = await axios.get(`directories/${id}`);
              setDirectory(response.data);
          }catch(error){
            navigate(-1);
          }
        }
        getDirectory();
    }, [id, navigate]
    )
    return (
    <div className = "all-directories-container">
        <div>Отдел: {directory?.department}</div>
        <div>Имя папки: {directory?.directoryName}</div>
        <button onClick = {()=> {navigate(`/chat/${id}`)}}>Задать вопрос чат-боту</button>
        <AllFiles/>
    </div>
    )
}

export default Directory;