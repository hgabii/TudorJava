import React, { Component } from "react";
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import API from "../api";
import "./NewQuestion.css";

export default class NewNote extends Component {
  constructor(props) {
    super(props);
    this.state = {
      content: ""
    };
  }

  validateForm() {
    return this.state.content.length > 0;
  }

  handleChange = event => {
    this.setState({
      [event.target.id]: event.target.value
    });
  }

  handleSubmit = async event => {
    event.preventDefault();

    API.post("question/create", {"text": this.state.content, "topicId": 1})
    .then(function(response) {
      console.log(response);
    }.bind(this))
    .catch(function(error) {
      console.log(error);
    });
  }

  render() {
    return (
      <div className="NewQuestion">
        <form onSubmit={this.handleSubmit}>
          <FormGroup controlId="content">
            <FormControl
              onChange={this.handleChange}
              value={this.state.content}
              componentClass="textarea"
            />
          </FormGroup>
          <Button
            block
            bsSize="large"
            disabled={!this.validateForm()}
            type="submit"
          >
            Létrehozás
          </Button>
        </form>
      </div>
    );
  }
}