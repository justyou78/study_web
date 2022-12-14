import { useTab } from '@mui/base';
import { Container, List, Paper } from '@mui/material';
import { useState } from 'react';
import AddTodo from './AddTodo';
import './App.css';
import Todo from './Todo';

function App() {
  const [items, setItems] = useState([]);

  const addItem = (item) => {
    item.id = 'ID-' + items.length;
    item.done = false;
    setItems([...items, item]);
    console.log('items : ', items);
  };

  const deleteItem = (item) => {
    const newItems = items.filter((e) => e.id !== item.id);
    setItems(newItems);
  };

  const todoItems = items.length > 0 && (
    <Paper style={{ margin: 16 }}>
      <List>
        {items.map((item) => (
          <Todo deleteItem={deleteItem} item={item} key={item.id} />
        ))}
      </List>
    </Paper>
  );
  return (
    <div className='App'>
      <Container maxWidth='md'>
        <AddTodo addItem={addItem} />
        <div className='TodoList'>{todoItems}</div>
      </Container>
    </div>
  );
}

export default App;
