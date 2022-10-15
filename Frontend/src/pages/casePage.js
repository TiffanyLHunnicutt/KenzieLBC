import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import CaseClient from "../api/CaseClient";

class CasePage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onGetAll', 'onGet', 'onCreate', 'renderCase'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
        document.getElementById('get-by-id-form').addEventListener('submit', this.onGet);
        document.getElementById('create-form').addEventListener('submit', this.onCreate);
        this.client = new CaseClient();

        this.dataStore.addChangeListener(this.renderCase)
        this.onGetAll();
    }

    // Render Methods --------------------------------------------------------------------------------------------------

    async renderCase() {
        let resultArea = document.getElementById("result-info");

        const cases = this.dataStore.get("cases");

        if (cases) {
            let casesHTML = "";
            for (let cased of cases) {
                casesHTML += `<div class="card">
                <h2> ${cased.title} </h2>
                <h3> Posted on: ${cased.timeStamp} </h3>
                <h3> ${cased.caseId} </h3>
                <h4> By: ${cased.author} </h4>
                <h4> Location Of Crime: ${cased.location} </h4>
                <h4> Date Of crime: ${cased.timeDate} </h4>
                <p> ${cased.description} </p>
                <h4> Potential Suspects: ${cased.potentialSuspects} </h4>
                <h5> Open Case: ${cased.openCase} </h5>
                <a class="header_nav_link button" href="admin.html">See Evidence For Case</a>
                </div>
                `;
            }
            resultArea.innerHTML = casesHTML;
        } else {
            resultArea.innerHTML = "No open cases";
        }
    }

    // Event Handlers --------------------------------------------------------------------------------------------------

    async onGetAll() {
        let result = await this.client.getAllOpenCases(this.errorHandler);
        this.dataStore.set("cases", result);
    }

    async onGet(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();
        let resultArea = document.getElementById("retrieved-case");

        let id = document.getElementById("id-field").value;

        let result = await this.client.getCase(id, this.errorHandler);
        this.dataStore.set("case", result);
        if (result) {
            this.showMessage(`Got ${result.title}!`);
            resultArea.innerHTML = `<div class="card">
            <h2> ${result.title} </h2>
            <h3> Posted on: ${result.timeStamp} </h3>
            <h3> ${result.caseId} </h3>
            <h4> By: ${result.author} </h4>
            <h4> Location Of Crime: ${result.location} </h4>
            <h4> Date Of crime: ${result.timeDate} </h4>
            <p> ${result.description} </p>
            <h4> Potential Suspects: ${result.potentialSuspects} </h4>
            <h5> Open Case: ${result.openCase} </h5>
            </div>
            `;
        } else {
            this.errorHandler("Error doing GET!  Try again...");
        }
    }

    async onCreate(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        let title = document.getElementById("create-title-field").value;
        let author = document.getElementById("create-author-field").value;
        let location = document.getElementById("create-location-field").value;
        let timeDate = document.getElementById("create-time-date-field").value;
        let description = document.getElementById("create-description-field").value;
        let potentialSuspects = document.getElementById("create-potential-suspects-field").value;

        const createdCase = await this.client.createCase(title, author, description, location, timeDate, potentialSuspects, this.errorHandler);

        if (createdCase) {
            this.showMessage(`Created ${createdCase.title}!`);
            this.onGetAll();
        } else {
            this.errorHandler("Error creating! Try again...");
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const casePage = new CasePage();
    casePage.mount();
};

window.addEventListener('DOMContentLoaded', main);
