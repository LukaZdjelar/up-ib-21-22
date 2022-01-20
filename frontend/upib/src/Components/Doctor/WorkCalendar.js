import { Calendar, momentLocalizer } from "react-big-calendar";
import "react-big-calendar/lib/css/react-big-calendar.css";
import moment from "moment";
import { useState, useEffect } from "react";
import axios from "axios";

moment.locale("sr", {
  week: {
    dow: 1,
    doy: 1,
  },
});

let localizer = momentLocalizer(moment);

const WorkCalendar = (props) => {
  const [events, setEvents] = useState([]);
  let data = [];

  useEffect(() => {
    sendGetRequest(); // eslint-disable-next-line
  }, []);

  const sendGetRequest = async () => {
    const response = await axios.get(
      "http://localhost:8080/appointment/workcalendar/" +
        String(JSON.parse(localStorage.getItem("loggedUser")).id)
    );
    response.data.map((appointment) =>
      data.push({
        title: "Appointment",
        start: new Date(appointment.dateAndTime),
        end: new Date(appointment.endDateAndTime),
      })
    );
    setEvents(data);
  };

  return (
    <div>
      <Calendar
        localizer={localizer}
        events={events}
        startAccessor="start"
        endAccessor="end"
        step="15"
        defaultView="week"
        min={new Date(2019, 1, 1, 7, 0)}
        max={new Date(2022, 1, 1, 19, 0)}
        selectable="true"
      />
    </div>
  );
};

export default WorkCalendar;
