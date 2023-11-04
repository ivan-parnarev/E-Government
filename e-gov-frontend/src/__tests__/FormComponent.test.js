import React from "react";
import { render, fireEvent, waitFor, screen } from "@testing-library/react";
import FormComponent from "../components/FormComponent";

test("input fields are rendered", () => {
  render(<FormComponent />);
  const nameInput = screen.getByText("Твоето име:");
  const voteInputs = screen.getAllByRole("radio");
  const submitButton = screen.getByText("Изпрати");
  expect(nameInput).toBeInTheDocument();
  expect(voteInputs.length).toBe(4);
  expect(submitButton).toBeInTheDocument();
});

// test("user can fill the form and submit it", async () => {
//   render(<FormComponent />);
//   const nameInput = screen.getByText("Твоето име:");
//   const voteInputs = screen.getAllByRole("radio");
//   const submitButton = screen.getByText("Изпрати");

//   fireEvent.change(nameInput, { target: { value: "Test User" } });
//   fireEvent.click(voteInputs[0]); // Selecting the first radio button

//   fireEvent.click(submitButton);

//   await waitFor(() => {
//     expect(
//       screen.getByText(
//         "Congrats, Test User, you've successfully voted for Бого"
//       )
//     ).toBeInTheDocument();
//   });
// });
