import { Outlet } from "react-router-dom";
import { MainNavigation } from "./components/MainNavigation";

export function RootLayout({ children }) {
  return (
    <>
      <MainNavigation />
      <Outlet>
        <main>{children}</main>
      </Outlet>
    </>
  );
}
