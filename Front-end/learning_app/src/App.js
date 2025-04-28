import "./App.css";
import Landing_Page from "./Pages/Landing_Page";
import Login_Page from "./Pages/Login_Page";
import Registration_Page from "./Pages/Registration_Page";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import User_Home_Page from "./Pages/User_Home_Page";
import FeedPage from "./Pages/Feed_page";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Landing_Page />} />
        <Route exact path="/Login" element={<Login_Page />} />
        <Route exact path="/Reg" element={<Registration_Page />} />
        <Route exact path="/Home" element={<User_Home_Page />} />
        <Route exact path="/Feed" element={<FeedPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
