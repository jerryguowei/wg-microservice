import React from 'react';
import { Router, Route, Switch } from 'react-router-dom';
import history from '../history';
import Test from './Test';

const App = () => {

    return (
        <Router history={history}>
            <div>
                <Switch>
                    <Route path="/" exact >
                        <Test></Test>
                    </Route>
                </Switch>
            </div>
        </Router>
    )
};

export default App;