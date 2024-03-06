import {
    createBrowserRouter,
    createRoutesFromElements,
    Route,
    RouterProvider,
} from 'react-router-dom';
import { Toaster } from 'react-hot-toast';
import { Provider } from 'react-redux';

import { RootLayout } from './RootLayout';
import { AuthProvider } from './hooks/AuthContext';
import store from './redux/store';

import { HomePage } from './pages/HomePage';
import { RegisterPage } from './pages/RegisterPage';
import { ActiveCampaignsPage } from './pages/ActiveCampaignsPage';
import { ProfilePage } from './pages/ProfilePage';
import { CreateCampaignPage } from './pages/CreateCampaignPage';
import { ResultsPage } from './pages/ResultsPage';
import { LandingPage } from './pages/LandingPage/LandingPage';

const routeChildren = [
    <Route key="home" index="true" element={<LandingPage />} />,
    <Route key="government" path="/government" element={<HomePage />} />,
    <Route key="register" path="/register" element={<RegisterPage />} />,
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
        <Provider store={store}>
            <AuthProvider>
                <Toaster position="top-right" />
                <RouterProvider router={router} />
            </AuthProvider>
        </Provider>
    );
}
