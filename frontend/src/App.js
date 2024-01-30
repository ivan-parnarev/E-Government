import {
  createBrowserRouter,
  createRoutesFromElements,
  Route,
  RouterProvider,
} from "react-router-dom";
import { RootLayout } from "./RootLayout";
import { HomePage } from "./pages/HomePage";
import { RegisterPage } from "./pages/RegisterPage";
import { ActiveCampaignsPage } from "./pages/ActiveCampaignsPage";
import { ProfilePage } from "./pages/ProfilePage";
import { AuthProvider } from "./hooks/AuthContext";
import { CreateCampaignPage } from "./pages/CreateCampaignPage";
import { ResultsPage } from "./pages/ResultsPage";

const routeChildren = [
  <Route key="home" index="true" element={<HomePage />} />,
  <Route key="register" path="/register" element={<RegisterPage />} />,
  <Route key="active-campaigns" path="/active-campaigns" element={<ActiveCampaignsPage />} />, //prettier-ignore
  <Route key="create-campaign" path="/create-campaign" element={<CreateCampaignPage />} />, //prettier-ignore
  <Route key="results" path="/results" element={<ResultsPage />} />,
  <Route key="profile" path="/profile" element={<ProfilePage />} />,
];

const routeDefinitions = createRoutesFromElements(
  <Route>
    <Route path="/" element={<RootLayout />} children={routeChildren} />
  </Route>
);

const router = createBrowserRouter(routeDefinitions);

export function App() {
  return (
    <AuthProvider>
      <RouterProvider router={router} />
    </AuthProvider>
  );
}
