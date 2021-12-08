import styles from "./ClinicCard.module.css";

const ClinicCard = (props) => {
  return (
    <li className={styles.clinic}>
      <div>
        <p>{props.name}</p>
        <p>{props.address}</p>
        <p>{props.description}</p>
      </div>
    </li>
  );
};

export default ClinicCard;
