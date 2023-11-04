import { Outlet } from "react-router-dom";
import MainNavigation from "./components/MainNavigation";

const RootLayout = ({ children }) => {
  return (
    <>
      <MainNavigation />
      <Outlet>
        <main>{children}</main>
      </Outlet>
    </>
  );
};

export default RootLayout;
