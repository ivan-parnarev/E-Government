export interface ElectionRowProps {
  candidateId: string;
  candidateName: string;
  candidateParty: string;
  candidateNumber: string;
  checked: boolean;
  onChange: (id: string, name: string, number: string) => void;
}
