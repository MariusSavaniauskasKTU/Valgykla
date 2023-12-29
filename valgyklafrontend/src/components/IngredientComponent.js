import React, { useEffect, useState } from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Container, Paper, Button, Typography, IconButton, Select, MenuItem, InputLabel } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';

export default function IngredientComponent() {
  const paperClasses = { padding: '50px 20px', width: 600, margin: '20px auto' };
  const [name, setName] = useState('');
  const [quantity, setQuantity] = useState(0);
  const [calories, setCalories] = useState(0);
  const [type, setType] = useState('');
  const [dishId, setDishId] = useState('');
  const [ingredients, setIngredients] = useState([]);
  const [dishes, setDishes] = useState([]);

  const handleClick = (e) => {
    e.preventDefault();
    const ingredient = { name, quantity, calories, type, dishId: parseInt(dishId) };
    console.log(ingredient);

    fetch("http://localhost:8080/ingredient", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(ingredient)
    })
      .then(() => {
        console.log("New ingredient added");
        // Refetch data after adding a new ingredient
        return fetch("http://localhost:8080/ingredient", { method: "GET" });
      })
      .then((res) => res.json())
      .then((result) => {
        setIngredients(result);
      });
  };

  const handleDelete = (id) => {
    // Send a request to delete the ingredient entry
    fetch(`http://localhost:8080/ingredient/${id}`, {
      method: "DELETE",
    })
      .then(() => {
        console.log("Ingredient deleted");
        // Refetch data after deleting the ingredient entry
        return fetch("http://localhost:8080/ingredient", { method: "GET" });
      })
      .then((res) => res.json())
      .then((result) => {
        setIngredients(result);
      });
  };

  useEffect(() => {
    // Fetch all dishes to populate the dropdown list
    fetch("http://localhost:8080/dish", { method: "GET" })
      .then((res) => res.json())
      .then((result) => {
        setDishes(result);
      });

    // Fetch all ingredients
    fetch("http://localhost:8080/ingredient", { method: "GET" })
      .then((res) => res.json())
      .then((result) => {
        setIngredients(result);
      });
  }, []);

  return (
    <Container>
      <Paper elevation={3} style={paperClasses}>
        <Typography variant="h4" align="center" gutterBottom>
          Pridėti ingredientą
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
          <TextField
            id="outlined-basic"
            label="Kalorijos"
            variant="outlined"
            fullWidth
            type="number"
            value={calories}
            onChange={(e) => setCalories(e.target.value)}
          />
          <TextField
            id="outlined-basic"
            label="Tipas"
            variant="outlined"
            fullWidth
            value={type}
            onChange={(e) => setType(e.target.value)}
          />
          <InputLabel id="dishId-label">Patiekalai</InputLabel>
          <Select
            labelId="dishId-label"
            id="dishId"
            value={dishId}
            label="Dish ID"
            onChange={(e) => setDishId(e.target.value)}
            fullWidth
          >
            {dishes.map((dish) => (
              <MenuItem key={dish.id} value={dish.id}>
                {dish.id}
              </MenuItem>
            ))}
          </Select>
          <Button variant="contained" onClick={handleClick}>
            Ideti
          </Button>
        </Box>
      </Paper>
      <Typography variant="h4" align="center" gutterBottom>
        Visi ingredientai
      </Typography>
      <Paper elevation={3} style={paperClasses}>
        {ingredients.map((ingredient) => (
          <Paper
            elevation={6}
            style={{
              margin: '10px',
              padding: '15px',
              textAlign: 'left',
              display: 'flex',
              justifyContent: 'space-between',
            }}
            key={ingredient.id}
          >
            <div>
              Id: {ingredient.id} <br />
              Pavadinimas: {ingredient.name} <br />
              Kiekis: {ingredient.quantity} <br />
              Kalorijos: {ingredient.calories} <br />
              Tipas: {ingredient.type} <br />
              Patiekalo ID: {ingredient.dishId}
            </div>
            <IconButton color="secondary" onClick={() => handleDelete(ingredient.id)}>
              <DeleteIcon />
            </IconButton>
          </Paper>
        ))}
      </Paper>
    </Container>
  );
}
