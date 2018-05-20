import React, { Component, Fragment } from "react";
import { Link, withRouter } from "react-router-dom";
import { Nav, Navbar, NavItem } from "react-bootstrap";
import { LinkContainer } from "react-router-bootstrap";
import { withCookies, Cookies } from 'react-cookie';
import { instanceOf } from 'prop-types';
import './App.css';
import Routes from "./Routes";
import API from './api';

class App extends Component {

  constructor(props) {
    super(props);
    
    this.state = {
      isAuthenticated: false,
      isAuthenticating: true,
      //cookies: instanceOf(Cookies).isRequired
    };
  }

  async componentDidMount() {
    try {
      var auth = localStorage.getItem("auth");
      if (auth) {
        //this.state.cookies.set('JSESSIONID', auth);
        this.userHasAuthenticated(true);
      }
    }
    catch(e) {
      if (e !== 'No current user') {
        alert(e);
      }
    }
  
    this.setState({ isAuthenticating: false });
  }

  userHasAuthenticated = authenticated => {
    this.setState({ isAuthenticated: authenticated });
  }

  handleLogout = event => {

    API.get("/logout")
      .then(function (response) {
        localStorage.setItem("auth", false);
        this.userHasAuthenticated(false);
        this.props.history.push("/login");
      }.bind(this))
      .catch(function (error) {
        console.log(error);
      });    
  }

  render() {

    const childProps = {
      isAuthenticated: this.state.isAuthenticated,
      userHasAuthenticated: this.userHasAuthenticated
    };

    return (
      <div className="App container">
        <Navbar fluid collapseOnSelect>
        <Navbar.Header>
          <Navbar.Brand>
            <Link to="/">Tudor</Link>
          </Navbar.Brand>
          <Navbar.Toggle />
        </Navbar.Header>
        <Navbar.Collapse>
          <Nav>
            <LinkContainer to="/questions/new">
              <NavItem>Kérdés létrehozása</NavItem>
            </LinkContainer>
          </Nav>
          <Nav pullRight>
          {this.state.isAuthenticated
            ? <NavItem onClick={this.handleLogout}>Kijelentkezés</NavItem>
            : <Fragment>
              <LinkContainer to="/login">
                <NavItem>Bejelentkezés</NavItem>
              </LinkContainer>
            </Fragment>
          }
          </Nav>
        </Navbar.Collapse>
      </Navbar>
        <Routes childProps={childProps}/>
      </div>
    );
  }
}

export default withRouter(withCookies(App));
