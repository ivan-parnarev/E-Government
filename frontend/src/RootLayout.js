import { Outlet, useLocation } from 'react-router-dom';
import { MainNavigation } from './components/MainNavigation';
import { LandingPageNav } from './components/LandingPageNav/LandingPageNav';
import BusinessNavigation from './components/BusinessNavigation/BusinessNavigation';

export function RootLayout({ children }) {
    const { pathname } = useLocation();

    const renderNavigation = () => {
        if (pathname === '/') {
            return <LandingPageNav />;
        } else if (pathname.startsWith('/business')) {
            return <BusinessNavigation />;
        } else {
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
