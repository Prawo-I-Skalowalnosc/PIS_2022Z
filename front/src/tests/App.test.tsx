import React from 'react';
import {render, screen} from '@testing-library/react';
import App from '../pages/App';
import '@testing-library/jest-dom'

test('renders learn react link', () => {
  render(<App />);
  expect(screen.getByText(/kliknij tutaj/i)).toBeInTheDocument();
});
