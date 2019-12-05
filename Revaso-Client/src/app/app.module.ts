import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatProgressSpinnerModule, MatProgressBarModule, MatButtonModule, MatIconModule, MatIcon, MatCardModule} from '@angular/material';
// import { MatProgressBar, MatProgressBarModule } from '@angular/material/progress-bar';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { SearchComponent } from './search/search.component';
import { NavbarComponent } from './navbar/navbar.component';
import { ProfileItemComponent } from './profile/profile-item/profile-item.component';
import { LoginComponent } from './login/login.component';
import { ProfilePageComponent } from './profile/profile-page/profile-page.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PostComponent } from './profile/profile-page/post/post.component';
import { ProfilesService } from './profile/profiles.service';
import { AuthService } from './profile/auth.service';
import { AuthGuard } from './profile/auth-guard.service';
import { PostService } from './profile/profile-page/post/post.service';
import { NewPostComponent } from './profile/profile-page/post/new-post/new-post.component';
import { RegisterComponent } from './register/register.component';
import { MyProfileComponent } from './profile/my-profile/my-profile.component';
import { LandingnavComponent } from './landingnav/landingnav.component';
import { FeedComponent } from './feed/feed.component';
import { EditprofileComponent } from './editprofile/editprofile.component';
import { ResetpasswordComponent } from './resetpassword/resetpassword.component';
import { CommentComponent } from './comment/comment.component';


@NgModule({
  declarations: [
    AppComponent,
    PageNotFoundComponent,
    SearchComponent,
    NavbarComponent,
    ProfileItemComponent,
    LoginComponent,
    ProfilePageComponent,
    PostComponent,
    NewPostComponent,
    RegisterComponent,
    MyProfileComponent,
    LandingnavComponent,
    FeedComponent,
    EditprofileComponent,
    ResetpasswordComponent,
    CommentComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatProgressSpinnerModule,
    MatProgressBarModule,
    MatButtonModule,
    MatIconModule,
    MatCardModule

  ],
  providers: [ProfilesService, AuthService, AuthGuard, PostService],
  bootstrap: [AppComponent]
})
export class AppModule { }
