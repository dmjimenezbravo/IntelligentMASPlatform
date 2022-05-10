<div id="top"></div>

<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/dmjimenezbravo/IntelligentMASPlatform">
    <h1 aling="center">Intelligent MAS Platform</h1>
  </a>

  <p align="center">
    A simple multi-agent platform for prediction of intances with machine learning algorithms.
    <br />
    <a href="https://github.com/dmjimenezbravo/IntelligentMASPlatform"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/dmjimenezbravo/IntelligentMASPlatform/issues">Report Bug</a>
    ·
    <a href="https://github.com/dmjimenezbravo/IntelligentMASPlatform/issues">Request Feature</a>
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li>
      <a href="#about-the-mas">About the MAS</a>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

A simple multi-agent platform for prediction of intances with machine learning algorithms. This is an example for the "Sistemas Inteligentes" ("Intelligent Systems") subject of the "Grado en Ingeniería Informática" ("Degree in Computer Engineering") degree at the Universidad Politécnica de Madrid (UPM) (Spain).

<p align="right">(<a href="#top">back to top</a>)</p>

### Built With

* [Java](https://www.java.com/).
* [JADE](https://jade.tilab.com/).
* [Weka](https://www.cs.waikato.ac.nz/ml/weka/).

<p align="right">(<a href="#top">back to top</a>)</p>


<!-- GETTING STARTED -->
## Getting Started

This project has been developed with the [Eclipse IDE](https://www.eclipse.org/ide/) and Java. Therefore, for the execution of the project we are going to assume that both softwares are installed on the machine.

<p align="right">(<a href="#top">back to top</a>)</p>

### Prerequisites

* [Java](https://www.java.com/).
* [Eclipse IDE](https://www.eclipse.org/ide/).

Although the use of the Eclipse IDE is recommended, any other Java development environment can be used.

<p align="right">(<a href="#top">back to top</a>)</p>

### Installation

To install and test the project, follow these steps:

1. You need to download this repository and import the project into your IDE.
2. Download and import the JADE and Weka libraries for Java into your project.
3. Configure the execution of the project, for this in our ide we must specify that the main class of the project is `jade.Boot`. In addition to configure each one of the agents of our MAS it is necessary to include the following arguments to our execution, `-gui UserAgent:en.upm.si.intelligentMASPlatform.UserAgent;TrainAgent:en.upm.si.intelligentMASPlatform.TrainAgent;PredictAgent:en.upm.si.intelligentMASPlatform.PredictAgent;ResultsAgent:en.upm.si.intelligentMASPlatform.ResultsAgent`.

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- MAS -->
## About the MAS

Here we present the Multi-Agent System (MAS) proposed for this platform. The system is composed of a total of four agents:

<img src="https://github.com/dmjimenezbravo/IntelligentMASPlatform/blob/main/Images/MAS.png" alt="Multi-Agent System" width="800"/>

* User agent: it is in charge of interacting with the user and communicating to the rest of the agents the actions defined by the user through the interaction.
* Training agent: it is in charge of training the model based on the user's preferences and communicating the resulting model to the prediction agent.
* Prediction agent: performs the prediction of the instance configured by the user using the model trained in the training agent. After finishing the prediction, it will communicate the results to the results agent.
* Results agent: communicates the results and notifies the user agent that new preconditions or training can be performed.

The interactions and exchange of messages in the defined platform are shown below.

<img src="https://github.com/dmjimenezbravo/IntelligentMASPlatform/blob/main/Images/JADESniffer.png" alt="JADE Sniffer" width="600"/>

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- USAGE EXAMPLES -->
## Usage

When the project is executed, the following screen is displayed:

<img src="https://github.com/dmjimenezbravo/IntelligentMASPlatform/blob/main/Images/ConfigureTraining.png" alt="Training configuration" width="600"/>

This screen allows you to select a dataset (for this particular platform the dataset is located in the `data` folder) and a machine learning algorithm with which our platform model will be trained. Once the training is done, we can use the button at the bottom of the screen that will take us to the next screen.

<img src="https://github.com/dmjimenezbravo/IntelligentMASPlatform/blob/main/Images/ConfigurePrediction.png" alt="Prediction configuration" width="400"/>

In this screen you can select the values of the instances to be predicted. After introducing these values, press the button and the instance to be predicted will be sent. After making the prediction, the system will display the following screen:

<img src="https://github.com/dmjimenezbravo/IntelligentMASPlatform/blob/main/Images/ShowsResults.png" alt="Results view" width="350"/>

Once the results have been displayed, you can return to the initial screen by clicking on the accept button.

<p align="right">(<a href="#top">back to top</a>)</p>


<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#top">back to top</a>)</p>


<!-- LICENSE -->
## License

Distributed under the GNU General Public License v3.0. See `LICENSE` for more information.

<p align="right">(<a href="#top">back to top</a>)</p>


<!-- CONTACT -->
## Contact

Diego M. Jiménez Bravo - [@dmjimenezbravo](https://twitter.com/dmjimenezbravo) - dmjimenezbravo@gmail.com

Project Link: [https://github.com/dmjimenezbravo/IntelligentMASPlatform](https://github.com/dmjimenezbravo/IntelligentMASPlatform)

<p align="right">(<a href="#top">back to top</a>)</p>


<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

* [JADE, Java Agent DEvelopment Framework](https://jade.tilab.com/).
* [Weka](https://www.cs.waikato.ac.nz/ml/weka/).

<p align="right">(<a href="#top">back to top</a>)</p>
