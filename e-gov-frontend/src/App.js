import {
  createBrowserRouter,
  createRoutesFromElements,
  Route,
  RouterProvider,
} from "react-router-dom";
import RootLayout from "./RootLayout";
import HomePage from "./pages/HomePage";
import RegisterPage from "./pages/RegisterPage";
import ActiveCampaignsPage from "./pages/ActiveCampaignsPage";

const routeChildren = [
  <Route key="home" index="true" element={<HomePage />} />,
  <Route key="register" path="/register" element={<RegisterPage />} />,
  <Route
    key="active-campaigns"
    path="/active-campaigns"
    element={<ActiveCampaignsPage />}
  />,
];

const routeDefinitions = createRoutesFromElements(
  <Route>
    <Route path="/" element={<RootLayout />} children={routeChildren} />
  </Route>
);

const router = createBrowserRouter(routeDefinitions);

function App() {
  return <RouterProvider router={router} />;
}

export default App;
