import { ChangeEvent } from "react";

export interface UserGuestProps {
  pinValue: string;
  isValidPinValue: boolean;
  onChange: (event: ChangeEvent<HTMLInputElement>) => void;
}
