import styles from "./BigCard.module.css";

const BigCard = (props) => {
  return <div className={styles.card}>{props.children}</div>;
};

export default BigCard;
