import { MouseEvent } from "react";

export interface CampaignModalFooterProps {
  showQuestions: boolean;
  submitButtonDisabled?: string | null;
  continueButtonDisabled: boolean;
  buttonText: string;
  onContinue: () => void;
  onBack: () => void;
  onSubmit: (event: MouseEvent<HTMLButtonElement>) => void;
  onHide: () => void;
}
