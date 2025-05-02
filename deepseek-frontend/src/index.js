import React from 'react';
import ReactDOM from 'react-dom/client';
import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";
import './index.css';
import App from './App';
import AllDirectories from './components/AllDirectories';
import Directory from './components/Directory';
import reportWebVitals from './reportWebVitals';

const router = createBrowserRouter([
  {
    path: "/chat/:id",
    element: <App />,
  },
  {
    path:"/",
    element: <AllDirectories />
  },
  {
    path:"/directories/:id",
    element: <Directory />,
  }
]);

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
