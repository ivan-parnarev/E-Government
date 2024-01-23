import { MouseEvent } from "react";

export interface CampaignModalFooterProps {
  submitButtonDisabled?: string | null;
  buttonText: string;
  onSubmit: (event: MouseEvent<HTMLButtonElement>) => void;
  onHide: () => void;
}
