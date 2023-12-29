import React, { useEffect, useState } from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Container, Paper, Button, Select, MenuItem, InputLabel } from '@mui/material';
import Typography from '@mui/material/Typography';
import IconButton from '@mui/material/IconButton';
import DeleteIcon from '@mui/icons-material/Delete';  // Import DeleteIcon from Material-UI
import { useNavigate } from 'react-router-dom';

const DishComponent = () => {
  const paperClasses = { padding: '50px 20px', width: 600, margin: '20px auto' };
  const [name, setName] = useState('');
  const [quantity, setQuantity] = useState(0);
  const [meniuId, setMeniuId] = useState('');
  const [dishes, setDishes] = useState([]);
  const [menius, setMenius] = useState([]);
  const navigate = useNavigate();

  const handleDelete = (id) => {
    fetch(`http://localhost:8080/dish/${id}`, {
      method: "DELETE",
    })
      .then(() => {
        console.log(`Dish with ID ${id} deleted`);
        // Refetch data after deleting a dish
        return fetch("http://localhost:8080/dish", { method: "GET" });
      })
      .then((res) => res.json())
      .then((result) => {
        setDishes(result);
      });
  };

  const handleUpdate = (id) => {
    // Navigate to the update form using the dish id
    navigate(`/update-dish/${id}`);
  };

  const handleClick = (e) => {
    e.preventDefault();
    const dish = { name, quantity, meniuId: parseInt(meniuId) };
    console.log(dish);

    fetch("http://localhost:8080/dish", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(dish)
    })
      .then(() => {
        console.log("New dish added");
        // Refetch data after adding a new dish
        return fetch("http://localhost:8080/dish", { method: "GET" });
      })
      .then((res) => res.json())
      .then((result) => {
        setDishes(result);
      });
  };

  useEffect(() => {
    fetch("http://localhost:8080/dish", { method: "GET" })
      .then((res) => res.json())
      .then((result) => {
        setDishes(result);
      });

    fetch("http://localhost:8080/meniu", { method: "GET" })
      .then((res) => res.json())
      .then((result) => {
        setMenius(result);
      });
  }, []);

 return (
    <Container>
      <Paper elevation={3} style={paperClasses}>
        <Typography variant="h4" align="center" gutterBottom>
          Įdėti naują patiekalą
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
            label="Pavadinimas"
            variant="outlined"
            fullWidth
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
          <TextField
            id="outlined-basic"
            label="Kiekis"
            variant="outlined"
            fullWidth
            type="number"
            value={quantity}
            onChange={(e) => setQuantity(e.target.value)}
          />
          <InputLabel id="meniuId-label">Meniu</InputLabel>
          <Select
            labelId="meniuId-label"
            id="meniuId"
            value={meniuId}
            label="Meniu"
            onChange={(e) => setMeniuId(e.target.value)}
            fullWidth
          >
            {menius.map((meniu) => (
              <MenuItem key={meniu.id} value={meniu.id}>
                {meniu.id}
              </MenuItem>
            ))}
          </Select>
          <Button variant="contained" onClick={handleClick}>
            Pridėti
          </Button>
        </Box>
      </Paper>
      <Typography variant="h4" align="center" gutterBottom>
        Visi patiekalai
      </Typography>
      <Paper elevation={3} style={{ padding: '50px 20px', width: 600, margin: '20px auto' }}>
        {dishes.map((dish) => (
          <Paper
            elevation={6}
            style={{
              margin: '10px',
              padding: '15px',
              textAlign: 'left',
              display: 'flex',
              justifyContent: 'space-between',
            }}
            key={dish.id}
          >
            <div>
              Id: {dish.id} <br />
              Pavadinimas: {dish.name} <br />
              Kiekis: {dish.quantity} <br />
              MeniuId: {dish.meniuId}
            </div>
            <div>
              <IconButton color="secondary" onClick={() => handleDelete(dish.id)}>
                <DeleteIcon />
              </IconButton>
            </div>
          </Paper>
        ))}
      </Paper>
    </Container>
  );
};

export default DishComponent;