import { element, by, ElementFinder } from 'protractor';

export class VquestionComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-vquestion div table .btn-danger'));
    title = element.all(by.css('jhi-vquestion div h2#page-heading span')).first();

    async clickOnCreateButton() {
        await this.createButton.click();
    }

    async clickOnLastDeleteButton() {
        await this.deleteButtons.last().click();
    }

    async countDeleteButtons() {
        return this.deleteButtons.count();
    }

    async getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class VquestionUpdatePage {
    pageTitle = element(by.id('jhi-vquestion-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    creationDateInput = element(by.id('field_creationDate'));
    vquestionInput = element(by.id('field_vquestion'));
    vquestionDescriptionInput = element(by.id('field_vquestionDescription'));
    userSelect = element(by.id('field_user'));
    vtopicSelect = element(by.id('field_vtopic'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setCreationDateInput(creationDate) {
        await this.creationDateInput.sendKeys(creationDate);
    }

    async getCreationDateInput() {
        return this.creationDateInput.getAttribute('value');
    }

    async setVquestionInput(vquestion) {
        await this.vquestionInput.sendKeys(vquestion);
    }

    async getVquestionInput() {
        return this.vquestionInput.getAttribute('value');
    }

    async setVquestionDescriptionInput(vquestionDescription) {
        await this.vquestionDescriptionInput.sendKeys(vquestionDescription);
    }

    async getVquestionDescriptionInput() {
        return this.vquestionDescriptionInput.getAttribute('value');
    }

    async userSelectLastOption() {
        await this.userSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async userSelectOption(option) {
        await this.userSelect.sendKeys(option);
    }

    getUserSelect(): ElementFinder {
        return this.userSelect;
    }

    async getUserSelectedOption() {
        return this.userSelect.element(by.css('option:checked')).getText();
    }

    async vtopicSelectLastOption() {
        await this.vtopicSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async vtopicSelectOption(option) {
        await this.vtopicSelect.sendKeys(option);
    }

    getVtopicSelect(): ElementFinder {
        return this.vtopicSelect;
    }

    async getVtopicSelectedOption() {
        return this.vtopicSelect.element(by.css('option:checked')).getText();
    }

    async save() {
        await this.saveButton.click();
    }

    async cancel() {
        await this.cancelButton.click();
    }

    getSaveButton(): ElementFinder {
        return this.saveButton;
    }
}

export class VquestionDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-vquestion-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-vquestion'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
