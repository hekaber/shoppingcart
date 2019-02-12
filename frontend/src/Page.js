import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import LoginPage from './page/LoginPage';


const Page = () => (
    <Router>
        <Switch>
            <Route exact path="/login">
                <LoginPage />
            </Route>
        </Switch>
    </Router>
);

export default Page;
