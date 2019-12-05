import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
// import { ProfileListComponent } from './profile/profile-list/profile-list.component';
import { LoginComponent } from './login/login.component';
import { ProfileItemComponent } from './profile/profile-item/profile-item.component';
import { SearchComponent } from './search/search.component';
import { ProfilePageComponent } from './profile/profile-page/profile-page.component';
import { AuthGuard } from './profile/auth-guard.service';
import { NewPostComponent } from './profile/profile-page/post/new-post/new-post.component';
import { RegisterComponent } from './register/register.component';
import { MyProfileComponent } from './profile/my-profile/my-profile.component';
import { FeedComponent } from './feed/feed.component';
import { EditprofileComponent } from './editprofile/editprofile.component';
import { ResetpasswordComponent } from './resetpassword/resetpassword.component';


const routes: Routes = [

  {path: "editProfile", component: EditprofileComponent, 
  // canActivate: [AuthGuard]
},
  {path: 'feed', component: FeedComponent},
  {path: 'register', component: RegisterComponent},
  {path:'resetPassword', component: ResetpasswordComponent},
  {path: 'newpost',
  //  canActivate: [AuthGuard], 
   component: NewPostComponent},
  {path: 'myprofile', 
  // canActivate: [AuthGuard],
  component: MyProfileComponent},
  {path:'profilepage', 
  // canActivate:[AuthGuard], 
  component:ProfilePageComponent},
  // {path: 'profiles', 
  // canActivate: [AuthGuard],
  // component: ProfileListComponent, children: [{path: ":id", component: ProfilePageComponent}]},
  {path: 'login', component: LoginComponent},
  {path: 'search', 
  // canActivate: [AuthGuard],
   component: SearchComponent},
  {path: '', component: LoginComponent},
  {path: 'not-found', component: PageNotFoundComponent},
  {path: '**', redirectTo: '/not-found'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
