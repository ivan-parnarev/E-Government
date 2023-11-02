import styles from "./App.module.css";
import FormComponent from "./components/FormComponent";
import ListComponent from "./components/ListComponent";
import MainNavigation from "./components/MainNavigation";

function App() {
  return (
    <div className={styles.app}>
      <MainNavigation />
      <div className={styles.container}>
        <FormComponent />
        <ListComponent />
      </div>
    </div>
  );
}

export default App;
