import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import EvidenceClient from "../api/evidenceClient";

class EvidenceAdmin extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onGet', 'onGetCase', 'onCreate', 'renderEvidence', 'renderCaseFile'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
        document.getElementById('get-by-id-form').addEventListener('submit', this.onGet);
        document.getElementById('case-id-view').addEventListener('submit', this.onGetCase);
        document.getElementById('create-evidence-form').addEventListener('submit', this.onCreate);
        this.client = new EvidenceClient();

        this.dataStore.addChangeListener(this.renderEvidence);
        this.dataStore.addChangeListener(this.renderCaseFile);

        this.renderCaseFile();
        //this.fetchConcerts();
    }

    // Render Methods --------------------------------------------------------------------------------------------------

    async renderCaseFile() {
        let resultArea = document.getElementById("present-case");

        const caseFile = this.dataStore.get("case");

        if(caseFile) {
            resultArea.innerHTML = `<div class="card">
            <h2> ${caseFile.title} </h2>
            <h3> Posted on: ${caseFile.timeStamp} </h3>
            <h3> ${caseFile.caseId} </h3>
            <h4> By: ${caseFile.author} </h4>
            <h4> Location Of Crime: ${caseFile.location} </h4>
            <h4> Date Of crime: ${caseFile.timeDate} </h4>
            <p> ${caseFile.description} </p>
            <h4> Potential Suspects: ${caseFile.potentialSuspects} </h4>
            <h5> Open Case: ${caseFile.openCase} </h5>
            </div>
            `;
        } else {
            resultArea.innerHTML = "Case Not Present";
        }
    }


    async renderEvidence() {
            let resultArea = document.getElementById("result-info");

            const evidence = this.dataStore.get("evidence");

            if (evidence) {
//                resultArea.innerHTML = `
//                    <div>ID: ${/cases/{caseId}/evidence/{evidenceId}}</div>
//                    <div>Title: ${evidence.title}</div>
//                    <div>Post Date: ${evidence.timeStamp}</div>
//                `
            } else {
                resultArea.innerHTML = "No evidence";
            }
    }

    // Event Handlers --------------------------------------------------------------------------------------------------

    async onGetCase(event) {
            // Prevent the page from refreshing on form submit
            event.preventDefault();

            let id = document.getElementById("case-id-field").value;

            let result = await this.client.getCase(id, this.errorHandler);
            this.dataStore.set("case", result);
            if (result) {
                this.showMessage(`Got ${result.title}!`);
                this.renderCaseFile();
            } else {
                this.errorHandler("Error doing GET!  Try again...");
            }
    }

    async onGetAll(event) {
        let result = await this.client.getAllOpenCases(this.dataStore.get("case").caseId, this.errorHandler);
        this.dataStore.set("cases", result);
    }

    async onGet(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        let id = document.getElementById("id-field").value;
        this.dataStore.set("evidence", null);

        let result = await this.client.getEvidenceById(id, this.errorHandler);
        this.dataStore.set("evidence", result);
        if (result) {
            this.showMessage(`Got ${result.name}!`)
        } else {
            this.errorHandler("Error doing GET!  Try again...");
        }
    }

    async onCreate(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        let caseId = this.dataStore.get("case").caseId;
        let location = document.getElementById("create-location-field").value;
        let timeDate = document.getElementById("create-time-date-field").value;
        let description = document.getElementById("create-description-field").value;
        let author = document.getElementById("create-author-field").value;

        const createdEvidence = await this.client.createEvidence(caseId, author, description, location, timeDate, this.errorHandler);
        this.dataStore.set("evidence", createdEvidence);

        if (createdEvidence) {
            this.showMessage(`Created ${createdEvidence.evidenceId}!`);
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
    const evidenceAdmin = new EvidenceAdmin();
    evidenceAdmin.mount();
};

window.addEventListener('DOMContentLoaded', main);