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

  // it("renders the list after clicking the 'Генерирай' button", async () => {
  //   render(<ListComponent />);
  //   const generateButton = screen.getByText(/Генерирай/i);
  //   fireEvent.click(generateButton);
  //   await waitFor(() => {
  //     const listContainer = screen.getByRole("list");
  //     expect(listContainer).toBeInTheDocument();
  //   });
  // });

  // it("renders the correct number of votes after fetching data", async () => {
  //   render(<ListComponent />);
  //   const generateButton = screen.getByText(/Генерирай/i);
  //   fireEvent.click(generateButton);
  //   await waitFor(() => {
  //     const totalVotes = screen.getByText(/Общо гласували:/i);
  //     expect(totalVotes).toBeInTheDocument();
  //     expect(totalVotes.textContent).toMatch(/Общо гласували: \d+/i);
  //   });
  // });
});
