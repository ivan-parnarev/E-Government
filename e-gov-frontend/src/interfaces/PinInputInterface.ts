import { ChangeEvent } from "react";

export interface PinInputProps {
  pinValue: string;
  isValidPinValue: boolean;
  onChange: (event: ChangeEvent<HTMLInputElement>) => void;
}
