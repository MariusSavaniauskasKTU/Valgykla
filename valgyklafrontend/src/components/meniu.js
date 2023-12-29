import React, { useEffect } from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Container, Paper, Button, Typography, IconButton } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';

export default function Meniu() {
  const paperClasses = { padding: '50px 20px', width: 600, margin: '20px auto' };
  const [date, setDate] = React.useState('');
  const [type, setType] = React.useState('');
  const [menius, setMenius] = React.useState([]);

  const handleClick = (e) => {
    e.preventDefault();
    const meniu = { date, type };
    console.log(meniu);
    fetch("http://localhost:8080/meniu", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(meniu)
    })
      .then(() => {
        console.log("New meniu added");
        // Refetch data after adding a new menu
        return fetch("http://localhost:8080/meniu", { method: "GET" });
      })
      .then((res) => res.json())
      .then((result) => {
        setMenius(result);
      });
  };

  const handleDelete = (id) => {
    // Send a request to delete the menu entry
    fetch(`http://localhost:8080/meniu/${id}`, {
      method: "DELETE",
    })
      .then(() => {
        console.log("Meniu deleted");
        // Refetch data after deleting the menu entry
        return fetch("http://localhost:8080/meniu", { method: "GET" });
      })
      .then((res) => res.json())
      .then((result) => {
        setMenius(result);
      });
  };

  useEffect(() => {
    fetch("http://localhost:8080/meniu", { method: "GET" })
      .then((res) => res.json())
      .then((result) => {
        setMenius(result);
      });
  }, []); // Provide an empty dependency array to run the effect only once

  return (
    <Container>
      <Paper elevation={3} style={paperClasses}>
        {/* Centered heading */}
        <Typography variant="h4" align="center" gutterBottom>
          Pridėti meniu
        </Typography>
        <Box
          component="form"
          sx={{
            '& > :not(style)': { m: 1, width: '25ch' },
          }}
          noValidate
          autoComplete="off"
        >
          <TextField
            id="outlined-basic"
            variant="outlined"
            fullWidth
            type="date"
            value={date}
            onChange={(e) => setDate(e.target.value)}
          />
          <TextField
            id="outlined-basic"
            label="tipas"
            variant="outlined"
            fullWidth
            value={type}
            onChange={(e) => setType(e.target.value)}
          />
          <Button variant="contained" onClick={handleClick}>
            Ideti
          </Button>
        </Box>
      </Paper>
      {/* Centered heading */}
      <Typography variant="h4" align="center" gutterBottom>
        Visi meniu
      </Typography>
      <Paper elevation={3} style={paperClasses}>
        {menius.map((meniu) => (
          <Paper
            elevation={6}
            style={{
              margin: '10px',
              padding: '15px',
              textAlign: 'left',
              display: 'flex',
              justifyContent: 'space-between', // Move the delete button to the right
            }}
            key={meniu.id}
          >
            <div>
              Id: {meniu.id} <br />
              Data: {new Date(meniu.date).toLocaleDateString()} <br />
              Tipas: {meniu.type}
            </div>
            <IconButton color="secondary" onClick={() => handleDelete(meniu.id)}>
              <DeleteIcon />
            </IconButton>
          </Paper>
        ))}
      </Paper>
    </Container>
  );
}

