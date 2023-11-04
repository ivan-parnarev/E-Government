import styles from "./App.module.css";
import FormComponent from "./components/FormComponent";
import ListComponent from "./components/ListComponent";
import MainNavigation from "./components/MainNavigation";
import HomePage from "./pages/HomePage";

function App() {
  return (
    <div className={styles.app}>
      <MainNavigation />
      <HomePage />
      <div className={styles.container}>
        <FormComponent />
        <ListComponent />
      </div>
    </div>
  );
}

export default App;
