import { MouseEvent } from "react";

export interface VotingModalFooterProps {
  pinValueLength: number;
  isValidPinValue: boolean;
  showQuestions: boolean;
  checkedId: string | null;
  onContinue: () => void;
  onBack: () => void;
  onSubmit: (event: MouseEvent<HTMLButtonElement>) => void;
  onHide: () => void;
}
