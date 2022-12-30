import React from 'react';
import {render, screen} from '@testing-library/react';
import '@testing-library/jest-dom'
import {LoginForm} from "../components/LoginForm";

test('renders login form', () => {
    render(<LoginForm  onError={()=>{}} onSuccess={()=>{}}/>);
    expect(screen.getByText(/hasło/i)).toBeInTheDocument();
});
