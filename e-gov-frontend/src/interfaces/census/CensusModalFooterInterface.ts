import { MouseEvent } from "react";

export interface CensusModalFooterProps {
  pinValueLength: number;
  isValidPinValue: boolean;
  showQuestions: boolean;
  onContinue: () => void;
  onSubmit: (event: MouseEvent<HTMLButtonElement>) => void;
  onBack: () => void;
  onHide: () => void;
}
