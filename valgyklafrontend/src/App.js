import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Meniu from './components/meniu';
import DishComponent from './components/DishComponent';
import ButtonAppBar from './components/buttonappbar';
import IngredientComponent from './components/IngredientComponent';

function App() {
  return (
    <Router>
      <div>
        <ButtonAppBar />
        <Routes>
          <Route path="/meniu" element={<Meniu />} />
          <Route path="/dish" element={<DishComponent />} />
          <Route path="/ingredient" element={<IngredientComponent />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
