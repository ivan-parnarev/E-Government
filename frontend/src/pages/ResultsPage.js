import React, { useEffect } from "react";
import Chart from "chart.js/auto";
import styles from "./ResultsPage.module.css";

export function ResultsPage() {
  useEffect(() => {
    const ctx = document.getElementById("myChart").getContext("2d");

    const myChart = new Chart(ctx, {
      type: "bar",
      data: {
        labels: ["Label 1", "Label 2", "Label 3"],
        datasets: [
          {
            label: "Dataset 1",
            data: [10, 20, 30],
            backgroundColor: [
              "rgba(255, 99, 132, 0.2)",
              "rgba(54, 162, 235, 0.2)",
              "rgba(255, 206, 86, 0.2)",
            ],
            borderColor: [
              "rgba(255, 99, 132, 1)",
              "rgba(54, 162, 235, 1)",
              "rgba(255, 206, 86, 1)",
            ],
            borderWidth: 1,
          },
        ],
      },
    });

    return () => {
      myChart.destroy();
    };
  }, []);

  return (
    <div className={styles.containerBackground}>
      <div className={styles.container}>
        <div className={styles.resultsContainer}>
          <canvas id="myChart" width="400" height="400"></canvas>
        </div>
      </div>
    </div>
  );
}
