import styles from "./ActiveCampaignsPage.module.css";
import FormComponent from "../components/FormComponent";
import ListComponent from "../components/ListComponent";

function ActiveCampaignsPage() {
  return (
    <div className={styles.container}>
      <FormComponent />
      <ListComponent />
    </div>
  );
}

export default ActiveCampaignsPage;
