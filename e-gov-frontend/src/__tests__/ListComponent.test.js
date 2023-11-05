import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import ListComponent from "../components/ListComponent";

describe("ListComponent", () => {
  it("renders the list title", () => {
    render(<ListComponent />);
    const listTitle = screen.getByText(/Списък с гласове:/i);
    expect(listTitle).toBeInTheDocument();
  });

  it("renders the 'Генерирай' button", () => {
    render(<ListComponent />);
    const generateButton = screen.getByText(/Генерирай/i);
    expect(generateButton).toBeInTheDocument();
  });

  it("does not render the vote list initially", () => {
    render(<ListComponent />);
    const listContainer = screen.queryByRole("list");
    expect(listContainer).not.toBeInTheDocument();
  });
});
