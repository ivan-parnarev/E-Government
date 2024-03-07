import {
    createBrowserRouter,
    createRoutesFromElements,
    Route,
    RouterProvider,
} from 'react-router-dom';
import { Toaster } from 'react-hot-toast';
import { RootLayout } from './RootLayout';
import { HomePage } from './pages/HomePage';
import { ActiveCampaignsPage } from './pages/ActiveCampaignsPage';
import { ProfilePage } from './pages/ProfilePage';
import { AuthProvider } from './hooks/AuthContext';
import { CreateCampaignPage } from './pages/CreateCampaignPage';
import { ResultsPage } from './pages/ResultsPage';
import { LandingPage } from './pages/LandingPage/LandingPage';
import BusinessHomePage from './pages/BusinessHomePage/BusinessHomePage';
import LoginPage from './pages/BusinessLogin/LoginPage';
import RegisterPage from './pages/BusinessRegister/RegisterPage';

const routeChildren = [
    <Route key="home" index="true" element={<LandingPage />} />,
    <Route key="business" path="/business" element={<BusinessHomePage />} />,
    <Route key="login" path="/business/login" element={<LoginPage />} />,
    <Route key="login" path="/business/register" element={<RegisterPage />} />,
    <Route key="government" path="/government" element={<HomePage />} />,
    <Route key="active-campaigns" path="/active-campaigns" element={<ActiveCampaignsPage />} />,
    <Route key="create-campaign" path="/create-campaign" element={<CreateCampaignPage />} />,
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
            <Toaster position="top-right" />
            <RouterProvider router={router} />
        </AuthProvider>
    );
}
