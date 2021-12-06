import UserContext from "./user-context";

const UserProvider = (props) => {
  const userContext = {
    user: {},
  };

  return (
    <UserContext.Provider value={userContext}>
      {props.children}
    </UserContext.Provider>
  );
};

export default UserProvider;
