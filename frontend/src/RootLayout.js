import { Outlet, useLocation } from 'react-router-dom';
import { MainNavigation } from './components/MainNavigation';
import { LandingPageNav } from './components/LandingPageNav/LandingPageNav';

export function RootLayout({ children }) {
    const location = useLocation();

    const renderNavigation = () => {
        const { pathname } = location;

        if (pathname !== '/') {
            return <MainNavigation />;
        }
    };

    return (
        <>
            {renderNavigation()}
            <Outlet>
                <main>{children}</main>
            </Outlet>
        </>
    );
}
