import { Button, Grid, TextField } from '@mui/material';
import React, { useState } from 'react';

const AddTodo = (props) => {
  const [item, setItem] = useState({ title: '' });
  const addItem = props.addItem;

  const onInputChange = (e) => {
    console.log(e.currentTarget.value);
    setItem({ title: e.target.value });
  };

  const onButtonClick = () => {
    addItem(item);
    setItem({ title: '' });
  };

  const enterKeEvcentHandler = (e) => {
    if (e.key === 'Enter') {
      onButtonClick();
    }
  };

  return (
    <Grid container style={{ marginTop: 20 }}>
      <Grid xs={11} md={11} item style={{ paddingRight: 16 }}>
        <TextField
          placeholder='Add Todo here'
          fullWidth
          onChange={onInputChange}
        />
      </Grid>
      <Grid xs={1} md={1} item>
        <Button
          fullWidth
          style={{ height: '100%' }}
          color='secondary'
          variant='outlined'
          onClick={onButtonClick}
          onKeyUp={enterKeEvcentHandler}
        >
          +
        </Button>
      </Grid>
    </Grid>
  );
};

export default AddTodo;
