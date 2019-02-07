import { element, by, ElementFinder } from 'protractor';

export class VtopicComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-vtopic div table .btn-danger'));
    title = element.all(by.css('jhi-vtopic div h2#page-heading span')).first();

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

export class VtopicUpdatePage {
    pageTitle = element(by.id('jhi-vtopic-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    creationDateInput = element(by.id('field_creationDate'));
    vtopicTitleInput = element(by.id('field_vtopicTitle'));
    vtopicDescriptionInput = element(by.id('field_vtopicDescription'));
    userSelect = element(by.id('field_user'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setCreationDateInput(creationDate) {
        await this.creationDateInput.sendKeys(creationDate);
    }

    async getCreationDateInput() {
        return this.creationDateInput.getAttribute('value');
    }

    async setVtopicTitleInput(vtopicTitle) {
        await this.vtopicTitleInput.sendKeys(vtopicTitle);
    }

    async getVtopicTitleInput() {
        return this.vtopicTitleInput.getAttribute('value');
    }

    async setVtopicDescriptionInput(vtopicDescription) {
        await this.vtopicDescriptionInput.sendKeys(vtopicDescription);
    }

    async getVtopicDescriptionInput() {
        return this.vtopicDescriptionInput.getAttribute('value');
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

export class VtopicDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-vtopic-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-vtopic'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
