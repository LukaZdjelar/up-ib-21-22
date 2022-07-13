import {Navigate, Route, Routes} from "react-router-dom";
import "./App.css";
import {RouteService} from "./Service/RouteService";

function App() {
    return (
        <>
            <Routes>
                {RouteService.getAllowedRoutes().map(route => {
                    return <Route path={route.path} element={route.element}/>
                })}
                <Route path='*' element={<Navigate to={RouteService.redirect()} replace/>}/>
            </Routes>
        </>
    );
}

export default App;
