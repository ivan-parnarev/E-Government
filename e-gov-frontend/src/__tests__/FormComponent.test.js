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