import { useState } from "react";
import CreateCampaignModalComponent from "./CreateCampaignModalComponent";

export function CreateCampaignContainerComponent() {
  const [createCampaignModalShow, setCreateCampaignModalShow] = useState(true);

  return (
    <div>
      <CreateCampaignModalComponent
        show={createCampaignModalShow}
        onHide={() => setCreateCampaignModalShow(false)}
        size="lg"
        aria-labelledby="contained-modal-title-vcenter"
        centered
      />
    </div>
  );
}
