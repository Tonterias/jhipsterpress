import { element, by, ElementFinder } from 'protractor';

export class UrllinkComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-urllink div table .btn-danger'));
    title = element.all(by.css('jhi-urllink div h2#page-heading span')).first();

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

export class UrllinkUpdatePage {
    pageTitle = element(by.id('jhi-urllink-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    linkTextInput = element(by.id('field_linkText'));
    linkURLInput = element(by.id('field_linkURL'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setLinkTextInput(linkText) {
        await this.linkTextInput.sendKeys(linkText);
    }

    async getLinkTextInput() {
        return this.linkTextInput.getAttribute('value');
    }

    async setLinkURLInput(linkURL) {
        await this.linkURLInput.sendKeys(linkURL);
    }

    async getLinkURLInput() {
        return this.linkURLInput.getAttribute('value');
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

export class UrllinkDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-urllink-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-urllink'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
