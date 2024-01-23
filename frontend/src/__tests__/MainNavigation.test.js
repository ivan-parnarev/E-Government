import React from "react";
import { render, fireEvent } from "@testing-library/react";
import MainNavigation from "../components/MainNavigation";

describe("MainNavigation", () => {
  test("renders navbar with logo", () => {
    const { getByAltText } = render(<MainNavigation />);
    const logoElement = getByAltText("logo");
    expect(logoElement).toBeInTheDocument();
  });

  test("renders navbar links", () => {
    const { getByText } = render(<MainNavigation />);
    expect(getByText("Home")).toBeInTheDocument();
    expect(getByText("Register")).toBeInTheDocument();
    expect(getByText("Active Campaigns")).toBeInTheDocument();
  });

  test("renders specific classes", () => {
    const { container } = render(<MainNavigation />);
    const navbarElement = container.querySelector(".navbar");
    const navbarNavLinkElements = container.querySelectorAll(".navbarNavLink");
    const navbarLogoImageElement = container.querySelector(".navbarLogoImage");

    expect(navbarElement).toBeInTheDocument();
    expect(navbarNavLinkElements.length).toBe(3);
    expect(navbarLogoImageElement).toBeInTheDocument();
  });
});
