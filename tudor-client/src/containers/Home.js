import React, { Component } from "react";
import { PageHeader, ListGroup,ListGroupItem } from "react-bootstrap";
import "./Home.css";
import API from "../api";

export default class Home extends Component {
  constructor(props) {
    super(props);

    this.state = {
      questions: []
    };
  }

  async componentDidMount() {
    if (!this.props.isAuthenticated) {
      return;
    }
  
    try {
      API.get("question/list")
      .then(function(response) {
        this.setState({ response });
      }.bind(this))
      .catch(function(error) {
        console.log(error);
      });
    } catch (e) {
      alert(e);
    }
  
    this.setState({ isLoading: false });
  }

  renderQuestionsList(questions) {

    return [{}].concat(questions).map(
      (question, i) =>
        i !== 0
          ? <ListGroupItem
              key={question.id}
              href={`/questions/${question.id}`}
              onClick={this.handleQuestionClick}
              header={question.text.trim().split("\n")[0]}
            >
              {"Created: " + new Date(question.creationDate).toLocaleString()}
            </ListGroupItem>
          : <ListGroupItem
              key="new"
              href="/questions/new"
              onClick={this.handleNoteClick}
            >
              <h4>
                <b>{"\uFF0B"}</b> Tegyél fel új kérdést
              </h4>
            </ListGroupItem>
    );
  }
  
  handleQuestionClick = event => {
    event.preventDefault();
    this.props.history.push(event.currentTarget.getAttribute("href"));
  }

  renderLander() {
    return (
      <div className="lander">
        <h1>Tudor portál</h1>
        <p>Felteheted kérdéseidet és a tudorok válaszolnak rá.</p>
      </div>
    );
  }

  renderQuestions() {
    return (
      <div className="questions">
        <PageHeader>A kérdéseid</PageHeader>
        <ListGroup>
          {this.renderQuestionsList(this.state.questions)}
        </ListGroup>
      </div>
    );
  }

  render() {
    return (
      <div className="Home">
        {this.props.isAuthenticated ? this.renderQuestions() : this.renderLander()}
      </div>
    );
  }
}